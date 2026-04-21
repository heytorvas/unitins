import subprocess
import os
import sys

# --- CONFIGURATION ---
GITHUB_USERNAME = "heytorvas"
REPOSITORIES = [
    "alg2-locadora",
    "top1-form-contato",
    "top1-crud-aviao",
    "ed-a1",
    "chat-bot",
    "top2-a1",
    "chat-socket",
    "top2-a2",
    "top1-cinema",
    "projeto-irrigacao",
    "so-terminal",
    "top3-calculadora",
    "top3-api-form",
    "pi2-crud-faq",
    "es2-documentacao",
    "cos-flask",
    "top3-a1",
    "spring-chatbot",
    "pi2-mockito",
    "dm1-tasks",
    "projetosia",
    "sd-sum_times"
]

def run(command):
    """Executes a command and returns output, or raises error on failure."""
    result = subprocess.run(command, shell=True, capture_output=True, text=True)
    if result.returncode != 0:
        return None, result.stderr.strip()
    return result.stdout.strip(), None

def get_default_branch(remote_name):
    """Detects if the remote uses 'main', 'master', or something else."""
    out, err = run(f"git remote show {remote_name}")
    if out:
        for line in out.split('\n'):
            if "HEAD branch" in line:
                return line.split(':')[-1].strip()
    return "main" # Fallback

def migrate():
    # 1. Validation: Ensure we are in a Git repo
    if not os.path.exists(".git"):
        print("Error: Run this inside the root of your target Git repository.")
        sys.exit(1)

    for repo in REPOSITORIES:
        print(f"\n🚀 Starting migration for: {repo}")
        
        # Check if folder already exists to avoid duplicates
        if os.path.exists(repo):
            print(f"⚠️ Folder '{repo}' already exists. Skipping...")
            continue

        url = f"https://github.com/{GITHUB_USERNAME}/{repo}.git"
        remote_name = f"temp_{repo}"

        existing_remotes, _ = run("git remote")
        if existing_remotes and remote_name in existing_remotes:
            print(f"🧹 Cleaning up leftover remote: {remote_name}")
            run(f"git remote remove {remote_name}")

        # 2. Add and Fetch Remote
        _, err = run(f"git remote add {remote_name} {url}")
        if err:
            print(f"❌ Failed to add remote: {err}")
            continue

        print(f"📥 Fetching history from {url}...")
        _, err = run(f"git fetch {remote_name}")
        if err:
            print(f"❌ Failed to fetch: {err}")
            run(f"git remote remove {remote_name}")
            continue

        # 3. Detect Branch and Merge
        branch = get_default_branch(remote_name)
        print(f"🔗 Merging branch '{branch}' into folder '{repo}'...")
        
        # The 'subtree add' command does the heavy lifting of rewriting history into a prefix
        _, err = run(f"git subtree add --prefix={repo} {remote_name} {branch}")
        
        if err and "working tree has modifications" in err:
            print("❌ Error: You have uncommitted changes. Commit them and try again.")
            break
        elif err:
            print(f"❌ Subtree error: {err}")
        else:
            print(f"✅ Successfully migrated {repo} with history.")

        # 4. Cleanup
        run(f"git remote remove {remote_name}")

if __name__ == "__main__":
    migrate()
    print("\n✨ All migrations finished. Run 'git log --graph' to see your combined history.")